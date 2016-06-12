from __future__ import print_function
import sys

from desktopmagic.screengrab_win32 import (
    getDisplayRects, saveScreenToBmp, saveRectToBmp, getScreenAsImage,
    getRectAsImage, getDisplaysAsImages)


entireScreen=getScreenAsImage()
entireScreen.save('screencapture.png',format='png')
