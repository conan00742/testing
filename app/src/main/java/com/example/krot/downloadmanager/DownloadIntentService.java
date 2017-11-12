package com.example.krot.downloadmanager;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by krot on 10/10/17.
 */

public class DownloadIntentService extends IntentService{

    public static String NAME = "krot";
    public static final String DOWNLOAD_ACTION = "com.example.krot.downloadmanager.DOWNLOAD";
    private DownloadManager downloadManager;

    public DownloadIntentService() {
        super(NAME);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        long refId;
        Uri uri = Uri.parse(MainActivity.fileUrl);
        downloadManager = (DownloadManager) this.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

//        request.setTitle("Downloading Music");
//        request.setDescription("Downloading " + MainActivity.displayName);
//        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment());
        Log.i("url", uri.getLastPathSegment());
        Intent downloadIntent = new Intent();
        refId = downloadManager.enqueue(request);
        downloadIntent.putExtra("downloadId", refId);
        downloadIntent.setAction(DOWNLOAD_ACTION);
        sendBroadcast(downloadIntent);
    }

}
