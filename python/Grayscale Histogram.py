'''
 * Generate a grayscale histogram for an image.
 *
 * Usage: python GrayscaleHistogram.py <fiilename> 
'''
import sys, os
import cv2
import numpy as np
from matplotlib import pyplot as plt

def get_path_and_extension(filename):
    index = filename.find('.')
    return filename[:index], filename[index + 1:]

# file_name = sys.argv[1]
file_name = "./image/back3.bmp"
# fname, fext = get_path_and_extension(file_name)
fname, fext = os.path.splitext(file_name)

# read image, based on command line filename argument;
# read the image as grayscale from the outset
# img = cv2.imread(sys.argv[1], cv2.IMREAD_GRAYSCALE)
img = cv2.imread(file_name, cv2.IMREAD_GRAYSCALE)
# height, width = img.shape[:2]

# create a mask
mask = np.zeros(img.shape[:2], np.uint8)

if "bottom" in fname:
    mask[470:940, 210:1720] = 255
    mask_img = cv2.bitwise_and(img, img, mask = mask)

'''
Normalization is a process that changes the range of pixel intensity values. 
Sometimes called contrast stretching or histogram stretching.
'''
# Normalize image
nor_img = np.zeros(img.shape[:2], np.uint8)
nor_img = cv2.normalize(img,nor_img, 0, 255, cv2.NORM_MINMAX)
cv2.imwrite((fname + "_nor." + fext), nor_img)

if "bottom" in fname:
    nor_mask_img = np.zeros(img.shape[:2], np.uint8)
    nor_mask_img = cv2.normalize(mask_img,nor_mask_img, 0, 255, cv2.NORM_MINMAX)

'''
Theory: Consider an image whose pixel values are confined to some specific range of values only. 
For eg, brighter image will have all pixels confined to high values. But a good image will have 
pixels from all regions of the image. So you need to stretch this histogram to either ends 
(as given in below image, from wikipedia) and that is what Histogram Equalization does 
(in simple words). This normally improves the contrast of the image.
'''
# Histogram equalization
equ_img = cv2.equalizeHist(img)
cv2.imwrite((fname + "_equ." + fext), equ_img)

if "bottom" in fname:
    equ_mask_img = cv2.equalizeHist(mask_img)

'''
Adaptive Histogram Equalization: image is divided into small blocks called "tiles". 
Then each of these blocks are histogram equalized as usual. So in a small area, 
histogram would confine to a small region (unless there is noise). 
If noise is there, it will be amplified. 
'''
# create a CLAHE object (Arguments are optional).
clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8,8))
clahe_img = clahe.apply(img)
cv2.imwrite((fname + "_clahe." + fext), clahe_img)

if "bottom" in fname:
    clahe_mask_img = clahe.apply(mask_img)
    
# Calculate histogram with mask and without mask
# Check third argument for mask
hist_full = cv2.calcHist([img],[0],None,[256],[0,256])
if "bottom" in fname:
    hist_mask = cv2.calcHist([img],[0],mask,[256],[0,256])

hist_nor = cv2.calcHist([nor_img],[0],None,[256],[0,256])
if "bottom" in fname:
    hist_mask_nor = cv2.calcHist([nor_img],[0],mask,[256],[0,256])

hist_equ = cv2.calcHist([equ_img],[0],None,[256],[0,256])
if "bottom" in fname:
    hist_mask_equ = cv2.calcHist([equ_img],[0],mask,[256],[0,256])

hist_clahe = cv2.calcHist([clahe_img],[0],None,[256],[0,256])
if "bottom" in fname:
    hist_mask_clahe = cv2.calcHist([clahe_img],[0],mask,[256],[0,256])

# Display
if "bottom" in fname:
    plt.subplot(3,4,1), plt.imshow(img, 'gray')
    plt.subplot(3,4,2), plt.imshow(equ_img, 'gray')
    plt.subplot(3,4,3), plt.imshow(clahe_img, 'gray')
    plt.subplot(3,4,4), plt.imshow(nor_img, 'gray')

    plt.subplot(3,4,5), plt.imshow(mask_img, 'gray')
    plt.subplot(3,4,6), plt.imshow(equ_mask_img,'gray')
    plt.subplot(3,4,7), plt.imshow(clahe_mask_img,'gray')
    plt.subplot(3,4,8), plt.imshow(nor_mask_img,'gray')

    plt.subplot(3,4,9), plt.plot(hist_full), plt.plot(hist_mask)
    plt.subplot(3,4,10), plt.plot(hist_mask_equ), plt.plot(hist_mask)
    plt.subplot(3,4,11), plt.plot(hist_mask_clahe), plt.plot(hist_mask)
    plt.subplot(3,4,12), plt.plot(hist_mask_nor), plt.plot(hist_mask)
else:
    plt.subplot(2,4,1), plt.imshow(img, 'gray')
    plt.subplot(2,4,2), plt.imshow(equ_img, 'gray')
    plt.subplot(2,4,3), plt.imshow(clahe_img, 'gray')
    plt.subplot(2,4,4), plt.imshow(nor_img, 'gray')

    plt.subplot(2,4,5), plt.plot(hist_full)
    plt.subplot(2,4,6), plt.plot(hist_full), plt.plot(hist_equ)
    plt.subplot(2,4,7), plt.plot(hist_full), plt.plot(hist_clahe)
    plt.subplot(2,4,8), plt.plot(hist_full), plt.plot(hist_nor)

plt.xlim([0,256])

plt.show()

'''
# display the image
cv2.namedWindow("Grayscale Image", cv2.WINDOW_NORMAL)
cv2.imshow("Grayscale Image", img)
# cv2.waitKey(0)

# create the histogram
histogram = cv2.calcHist([img], [0], None, [256], [0, 256])
# print(histogram)

# configure and draw the histogram figure
plt.figure()
plt.title("Grayscale Histogram")
plt.xlabel("grayscale value")
plt.ylabel("pixels")
plt.xlim([0, 256])

plt.plot(histogram)
plt.show()
'''