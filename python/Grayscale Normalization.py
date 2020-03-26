'''
 * Generate a grayscale histogram for an image.
 *
 * Usage: python GrayscaleHistogram.py <fiilename> 
'''
import os
import cv2
import numpy as np
from matplotlib import pyplot as plt

'''
file_name1 = "./image/back1.bmp"
file_name2 = "./image/back2.bmp"
file_name3 = "./image/back3.bmp"
'''
file_name1 = "./image/front1.bmp"
file_name2 = "./image/front2.bmp"
file_name3 = "./image/front3.bmp"

# read image, based on command line filename argument;
# read the image as grayscale from the outset
# img = cv2.imread(sys.argv[1], cv2.IMREAD_GRAYSCALE)
org_img1 = cv2.imread(file_name1, cv2.IMREAD_GRAYSCALE)
org_img2 = cv2.imread(file_name2, cv2.IMREAD_GRAYSCALE)
org_img3 = cv2.imread(file_name3, cv2.IMREAD_GRAYSCALE)

'''
Normalization is a process that changes the range of pixel intensity values. 
Sometimes called contrast stretching or histogram stretching.
'''
# Normalize image
nom_img1 = np.zeros(org_img1.shape[:2], np.uint8)
nom_img1 = cv2.normalize((org_img1 + 28), nom_img1, 0, 255, cv2.NORM_MINMAX)

fname, fext = os.path.splitext(file_name1)
cv2.imwrite((fname + "_nom" + fext), nom_img1)

nom_img2 = np.zeros(org_img2.shape[:2], np.uint8)
nom_img2 = cv2.normalize(org_img2, nom_img2, 0, 255, cv2.NORM_MINMAX)

fname, fext = os.path.splitext(file_name2)
cv2.imwrite((fname + "_nom" + fext), nom_img2)

nom_img3 = np.zeros(org_img3.shape[:2], np.uint8)
nom_img3 = cv2.normalize((org_img3 - 28), nom_img3, 0, 255, cv2.NORM_MINMAX)

fname, fext = os.path.splitext(file_name3)
cv2.imwrite((fname + "_nom" + fext), nom_img3)

# Calculate histogram
org_hist1 = cv2.calcHist([org_img1],[0],None,[256],[0,256])
nom_hist1 = cv2.calcHist([nom_img1],[0],None,[256],[0,256])

org_hist2 = cv2.calcHist([org_img2],[0],None,[256],[0,256])
nom_hist2 = cv2.calcHist([nom_img2],[0],None,[256],[0,256])

org_hist3 = cv2.calcHist([org_img3],[0],None,[256],[0,256])
nom_hist3 = cv2.calcHist([nom_img3],[0],None,[256],[0,256])

plt.subplot(3,3,1), plt.imshow(org_img1, 'gray')
plt.subplot(3,3,2), plt.imshow(org_img2, 'gray')
plt.subplot(3,3,3), plt.imshow(org_img3, 'gray')

plt.subplot(3,3,4), plt.imshow(nom_img1, 'gray')
plt.subplot(3,3,5), plt.imshow(nom_img2, 'gray')
plt.subplot(3,3,6), plt.imshow(nom_img3, 'gray')

plt.subplot(3,3,7), plt.plot(org_hist1), plt.plot(org_hist2), plt.plot(org_hist3)
plt.subplot(3,3,8), plt.plot(nom_hist1), plt.plot(nom_hist2), plt.plot(nom_hist3)

plt.xlim([0,256])

plt.show()
