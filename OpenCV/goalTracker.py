import os
import numpy as np
import cv2
from networktables import NetworkTable

os.system("sudo bash /home/pi/vision/init.sh")

NetworkTable.setIPAddress("roboRIO-4914-FRC.local")
NetworkTable.setClientMode()
NetworkTable.initialize()
table = NetworkTable.getTable("HookContoursReport")

COLOR_MIN = np.array([60, 80, 80])
COLOR_MAX = np.array([85, 255, 255])
VIEW_ANGLE = 60 * 360 / 6.283185307 # (for lifecam 3000)
FOV_PIXEL = 320
HOOK_CAM_ID = 0
BOIL_CAM_ID = 1
DEBUG = False

# HOOK_TARGET_LENGTH = 51 # width of retroreflective tape, in cm
MIN_HOOK_AREA = 150

MIN_BOIL_AREA = 100

def cart2pol(a):
    x = a[0]
    y = a[1]
    rho = np.sqrt(x**2 + y**2)
    phi = np.arctan2(y, x)
    return([rho, phi])

def pol2cart(a):
    rho = a[0]
    phi = a[1]
    x = rho * np.cos(phi)
    y = rho * np.sin(phi)
    return([x, y])

def trackHook():
    # read image from camera, resize to 320x240, convert to HSV
    ret, frame = cap.read()
    frame=cv2.resize(frame, (0,0), fx=0.5, fy=0.5)
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)

    if DEBUG:
        cv2.imshow('hsv', hsv)
        cv2.imshow('brg', frame)

    # threshold image based on HSV range provided by COLOR_MIN and COLOR_MAX
    frame = cv2.inRange(hsv, COLOR_MIN, COLOR_MAX)

    if DEBUG:
        cv2.imshow('frame', frame)

    # find contours based on thresholded image
    _, contours, heirarchy = cv2.findContours(frame, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    # creates array of contours larger than given min area
    filteredContours = []
    for i in range(0, len(contours)):
        if cv2.contourArea(contours[i]) > MIN_HOOK_AREA:
            filteredContours.append(contours[i])

    # finds most rightward (highest x-val) contour from filtered contours
    if len(filteredContours) > 0:
        # default index and x value
        iTargetContour = 0;
        maxRightness = 0;

        # searches for index of most rightward contours
        for i in range(0, len(filteredContours)):

            # analyze centre X
            M = cv2.moments(filteredContours[i])
            cX = int(M["m10"] / M["m00"])
            if cX > maxRightness:
                maxRightness = cX
                iTargetContour = i

        targetContour = filteredContours[iTargetContour]

        M = cv2.moments(targetContour)
        cX = int(M["m10"] / M["m00"])
        cY = int(M["m01"] / M["m00"])

        print(cX, " ", cY)

	    table.putNumber('cX', cX)
	    table.putNumber('cY', cY)

    else: # if no contours found
        table.putNumber('cX', -1)
        table.putNumber('cY', -1)

# end of trackHook()

cap = cv2.VideoCapture(HOOK_CAM_ID)

while True:
    trackHook()

if DEBUG:
	cap.release()
	cv2.destroyAllWindows()
