from networktables import NetworkTable
import logging

logging.basicConfig(level=logging.DEBUG) # enables logging for pynetworktables
NetworkTable.setIPAddress("roboRIO-4914-FRC.local")
NetworkTable.setClientMode()
NewtorkTable.initialize()
table = NetworkTable.getTable("ContoursReport")

while True:
    if len(filteredContours) > 0:
        table.putNumber('isTarget', 1)
        table.putNumber('cX', cX)
        table.putNumber('cY', cY)
    else:
        table.putNumber('isTarget', 0)
        table.putNumber('cX', -1)
        table.putNumber('cY', -1)
