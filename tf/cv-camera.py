import cv2
import numpy as np
 
# Create a VideoCapture object and read from input file
# If the input is the camera, pass 0 instead of the video file name
cap = cv2.VideoCapture(0)
 
# Check if camera opened successfully
if (cap.isOpened()== False): 
  print("Error opening video stream or file")
 
print("Start camera using OpenCV %s" % cv2.__version__)

# Default resolutions of the frame are obtained.The default resolutions are system dependent.
# We convert the resolutions from float to integer.
frame_width = int(cap.get(3))
frame_height = int(cap.get(4))

print("The video resolution are: {} x {} ".format(frame_width, frame_height))

# Define the codec and create VideoWriter object.The output is stored in 'outpy.avi' file.
# out = cv2.VideoWriter('./image/capture.avi', cv2.VideoWriter_fourcc('M','J','P','G'), 10, (frame_width,frame_height))

# Read until video is completed
while(cap.isOpened()):
  # Capture frame-by-frame
  ret, frame = cap.read()
  if ret == True:
 
    # Write the frame into the file
    # out.write(frame)

    # Display the resulting frame
    cv2.imshow('Frame', frame)
 
    # Save image
    if cv2.waitKey(25) & 0xFF == ord('c'):
      cv2.imwrite("./image/capture.jpg", frame)

    # Press Q on keyboard to  exit
    if cv2.waitKey(25) & 0xFF == ord('q'):
      break
 
  # Break the loop
  else: 
    break
 
# When everything done, release the video capture object
cap.release()
# out.release()
 
# Closes all the frames
cv2.destroyAllWindows()
