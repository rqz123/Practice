import os
import cv2
import numpy as np
import numpy as np
from matplotlib import pyplot as plt

# Image file path
file_name = "./image/front2.bmp"
fname, fext = os.path.splitext(file_name)

# Read the image as grayscale from the outset
org_img = cv2.imread(file_name, cv2.IMREAD_GRAYSCALE)
# crop_img = org_img[1664:4096, 2048:4096] # [y:h, x:w]

'''
# Construct a SIFT/SURF object for image keypoint detection
sift = cv2.xfeatures2d.SIFT_create(nfeatures=1000)
# sift = cv2.xfeatures2d.SURF_create()

keypoints = sift.detect(crop_img,None)

# Draws the small circles on the locations of keypoints
kp_img = cv2.drawKeypoints(crop_img, keypoints, outImage=np.array([]), color=(255,0,0), flags=cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)

# Display image
imgplot = plt.imshow(kp_img)
'''

# Canny Edge Detection is a popular edge detection algorithm.
edges_img = cv2.Canny(org_img,threshold1=0,threshold2=255)
imgplot = plt.imshow(edges_img)

plt.show()
# cv2.waitKey(0)