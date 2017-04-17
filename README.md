# FRC-2017

Team 4914 Robot Game Code for **FIRST STEAMWORKS**

Latest project can be found under `Emmet2`

**Guidelines**
---------------
In order to contribute to this project, create a separate branch where you can make your changes.  Once you are ready to merge your changes with the master branch, open a pull request and somebody else will be able to review your code.  Anyone can make comments or provide feedback on pull requests. If everything is good to go, your pull request will be merged into the master branch!

Keeping your code simple, easy to understand and elegant is not easy, but it sure helps everyone else who has to look at it. If there's an easier way to do something, then do it!  It's much better to write efficient code, not just a hot mess.

Be sure to always document your code with meaningful comments; we're building clean code here!

**Reference**
---------------
[WPILib ScreenSteps Live (2017 FRC Control System)](http://wpilib.screenstepslive.com/s/4485)

[wpilibj Documentation](http://first.wpi.edu/FRC/roborio/release/docs/java/)

[TalonSRX 3rd Party Libraries](http://www.ctr-electronics.com/hro.html#product_tabs_technical_resources) - At the bottom, under the "Tech Resources" tab, download the latest installer, and follow on-screen instructions.

# OpenCV Vision

Before running vision on your Jetson or Pi, ensure that you are running Python 3.5 

## Running

Before running, make sure that you have the following:

Install numpy

`pip install numpy`

Install OpenCV for Python

`pip install opencv-python`

Ensure that you can export to a NetworkTable

`pip install pynetworktables`

Have the `init.sh` script run on startup if you are using a Microsoft LifeCam. In your robot code, request the NetworkTable and you are good to go.

