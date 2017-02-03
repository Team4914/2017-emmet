# set exposure
v4l2-ctl --device=/dev/video0 \
--set-ctrl=brightness=150 \
--set-ctrl=contrast=5 \
--set-ctrl=saturation=150 \
--set-ctrl=white_balance_temperature_auto=0 \
--set-ctrl=power_line_frequency=2 \
--set-ctrl=white_balance_temperature=4500 \
--set-ctrl=sharpness=25 \
--set-ctrl=backlight_compensation=0 \
--set-ctrl=exposure_auto=1 \
--set-ctrl=exposure_absolute=10 \
--set-ctrl=pan_absolute=0 \
--set-ctrl=tilt_absolute=0 \
--set-ctrl=zoom_absolute=0
