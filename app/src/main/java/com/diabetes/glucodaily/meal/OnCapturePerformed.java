package com.diabetes.glucodaily.meal;

import java.io.File;


public interface OnCapturePerformed {
    void loadCapturedFile(File file);

    void loadPosGlycemiaMeal(String imagePath, String posGlycemia);
    
}
