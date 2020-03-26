'''
 * Generate a grayscale histogram for an image.
 *
 * Usage: python GrayscaleHistogram.py <fiilename> 
'''
import os
import cv2
import numpy as np
from matplotlib import pyplot as plt


file_name = "./image/front2.bmp"
fname, fext = os.path.splitext(file_name)

# read image, based on command line filename argument;
# read the image as grayscale from the outset
org_img = cv2.imread(file_name, cv2.IMREAD_GRAYSCALE)

'''
Normalization is a process that changes the range of pixel intensity values. 
Sometimes called contrast stretching or histogram stretching.
'''
# Normalize image
nom_img = np.zeros(org_img.shape[:2], np.uint8)
nom_img = cv2.normalize(org_img, nom_img, 0, 255, cv2.NORM_MINMAX)
cv2.imwrite((fname + "_nom" + fext), nom_img)

'''
Theory: Consider an image whose pixel values are confined to some specific range of values only. 
For eg, brighter image will have all pixels confined to high values. But a good image will have 
pixels from all regions of the image. So you need to stretch this histogram to either ends 
(as given in below image, from wikipedia) and that is what Histogram Equalization does 
(in simple words). This normally improves the contrast of the image.
'''
# Histogram equalization
equ_img = cv2.equalizeHist(org_img)
cv2.imwrite((fname + "_equ" + fext), equ_img)

# Sharpness
# https://en.wikipedia.org/wiki/Kernel_(image_processing)
kernel = np.array([[-1,-1,-1], [-1, 9,-1], [-1,-1,-1]])
shp_img = cv2.filter2D(org_img, -1, kernel)
cv2.imwrite((fname + "_shp" + fext), shp_img)

'''
# Unsharp mask
# https://homepages.inf.ed.ac.uk/rbf/HIPR2/unsharp.htm
def unsharp_mask(image, kernel_size=(5, 5), sigma=1.0, amount=1.0, threshold=0):
    blurred = cv2.GaussianBlur(image, kernel_size, sigma)
    sharpened = float(amount + 1) * image - float(amount) * blurred
    sharpened = np.maximum(sharpened, np.zeros(sharpened.shape))
    sharpened = np.minimum(sharpened, 255 * np.ones(sharpened.shape))
    sharpened = sharpened.round().astype(np.uint8)
    if threshold > 0:
        low_contrast_mask = np.absolute(image - blurred) < threshold
        np.copyto(sharpened, image, where=low_contrast_mask)
    return sharpened

shp_img = unsharp_mask(org_img)
cv2.imwrite((fname + "_shp" + fext), shp_img)
'''
'''
Adaptive Histogram Equalization: image is divided into small blocks called "tiles". 
Then each of these blocks are histogram equalized as usual. So in a small area, 
histogram would confine to a small region (unless there is noise). 
If noise is there, it will be amplified. 
'''
# create a CLAHE object (Arguments are optional).
clahe = cv2.createCLAHE(clipLimit=2.0, tileGridSize=(8,8))
clahe_img = clahe.apply(org_img)
cv2.imwrite((fname + "_clahe" + fext), clahe_img)

# Calculate histogram with mask and without mask
# Check third argument for mask
org_hist = cv2.calcHist([org_img],[0],None,[256],[0,256])
nom_hist = cv2.calcHist([nom_img],[0],None,[256],[0,256])
equ_hist = cv2.calcHist([equ_img],[0],None,[256],[0,256])
shp_hist = cv2.calcHist([shp_img],[0],None,[256],[0,256])
clahe_hist = cv2.calcHist([clahe_img],[0],None,[256],[0,256])

# Display
plt.subplot(2,4,1), plt.imshow(org_img, 'gray')
plt.subplot(2,4,2), plt.imshow(nom_img, 'gray')
plt.subplot(2,4,3), plt.imshow(equ_img, 'gray')
plt.subplot(2,4,4), plt.imshow(clahe_img, 'gray')

plt.subplot(2,4,5), plt.plot(org_hist)
plt.subplot(2,4,6), plt.plot(org_hist), plt.plot(nom_hist)
plt.subplot(2,4,7), plt.plot(org_hist), plt.plot(equ_hist)
plt.subplot(2,4,8), plt.plot(org_hist), plt.plot(clahe_hist)

plt.xlim([0,256])

plt.show()
