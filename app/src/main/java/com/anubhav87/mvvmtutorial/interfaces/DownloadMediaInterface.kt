package com.anubhav87.mvvmtutorial.interfaces

import android.net.Uri

interface DownloadMediaInterface
{
   fun startDownload(downloadPath: String, description: String , position : Int)
}