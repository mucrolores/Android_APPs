package com.example.p44_dropboxcloudsave;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.Auth;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.DbxRawClientV2;
import com.dropbox.core.v2.common.PathRoot;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.sharing.DbxUserSharingRequests;
import com.dropbox.core.v2.sharing.ListFoldersResult;
import com.dropbox.core.v2.sharing.SharedFolderMetadata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private TextView MessageTextView;
    private Button LoginButton,ChooserButton;

    private boolean LoginRequested = false;
    private String ACCESS_TOKEN = null;
    private long TokenTimeTag;

    private SharedPreferences sharedPreferences;

    private String RootNameSpaceID;

    private DbxRequestConfig config;
    private DbxClientV2 client;
    private DbxRawClientV2 raw_client;
    private DbxUserSharingRequests dbxUserSharingRequests;

    private DropboxFileNode dropboxFileNode;
    private Queue<DropboxFileNode> TraversalQueue = new LinkedList<>();
    private ArrayList<DropboxFileNode> TraversalArrayList = new ArrayList<>();

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MessageTextView = findViewById(R.id.MessageTextView_ID);
        LoginButton = findViewById(R.id.LoginButton_ID);
        ChooserButton = findViewById(R.id.ChooserButton_ID);

        sharedPreferences = getSharedPreferences(getString(R.string.Dropbox_SP),MODE_PRIVATE);
        ACCESS_TOKEN = sharedPreferences.getString(getString(R.string.Dropbox_SP_AuthToken),null);
        TokenTimeTag = sharedPreferences.getLong(getString(R.string.Dropbox_SP_TokenTimeTag),0);

        long CurrentTimeMillis = System.currentTimeMillis();
        long Diff = CurrentTimeMillis-TokenTimeTag;

        StringBuilder FStringBuilder = new StringBuilder();

        FStringBuilder.append("TokenTimeTag : ").append(TokenTimeTag).append("\n")
                .append("TokenTimeData : ").append(DateFormat.getDateTimeInstance().format(TokenTimeTag)).append("\n")
                .append("CurrentMillis : ").append(CurrentTimeMillis).append("\n")
                .append("CurrentMillis : ").append(DateFormat.getDateTimeInstance().format(CurrentTimeMillis)).append("\n")
                .append("DiffTime : ").append(Diff).append("\n")
                .append("DiffDate : ").append(DateFormat.getDateTimeInstance().format(Diff)).append("\n")
                .append("ACCESSTOKEN : ").append(ACCESS_TOKEN).append("\n");

        if(ACCESS_TOKEN == null || Diff > 108L ) {
            LoginButton.setEnabled(true);
            FStringBuilder.append("Condition Count").append("\n");
        }
        else{
            LoginButton.setEnabled(false);
            FStringBuilder.append("Condition Not Count").append("\n");
        }
        FStringBuilder.append("Clickable : ").append(LoginButton.isEnabled()).append("\n");

        File cacheDir = this.getCacheDir();
        File[] files = cacheDir.listFiles();
        for(File tmpFile:files) {
            FStringBuilder.append(tmpFile.getPath()).append("\n");
            if(tmpFile.isFile()) {
                tmpFile.delete();
            }
        }
        MessageTextView.setText(FStringBuilder.toString());

        dropboxFileNode = new DropboxFileNode("/",null);

        SetLoginButton();

        //DropboxActivity.startOAuth2Authentication(MainActivity.this, "15j3ped0aid8hc6", Arrays.asList("account_info.read", "files.content.write"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(LoginRequested) {
            ACCESS_TOKEN = Auth.getOAuth2Token();
            sharedPreferences.edit().putString(getString(R.string.Dropbox_SP_AuthToken),ACCESS_TOKEN).apply();
            sharedPreferences.edit().putLong(getString(R.string.Dropbox_SP_TokenTimeTag),System.currentTimeMillis()).apply();

            DropboxClientFactory.init(ACCESS_TOKEN);
            StringBuilder ToShow = new StringBuilder();
            ToShow.append("Auth Token : ").append(ACCESS_TOKEN).append("\n");
            MessageTextView.setText(ToShow);
            //PicassoClient.init(getApplicationContext(), DropboxClientFactory.getClient());
            Toast.makeText(this, "Auth Token : " + ACCESS_TOKEN, Toast.LENGTH_SHORT).show();

            config = DbxRequestConfig.newBuilder("OSA_RIVA").build();
            client = new DbxClientV2(config, ACCESS_TOKEN);

            Thread thread = new Thread(()-> {
                try {
                    result = DropboxClientFactory.getClient().sharing().listFolders().toStringMultiline();
                } catch (DbxException e) {
                    e.printStackTrace();
                }
                runOnUiThread(()->{MessageTextView.setText(result);});

                /*try {
                    ToShow.append(DropboxClientFactory.getClient().users().getCurrentAccount().getName().getDisplayName()).append("\n");
                    //ToShow.append(DropboxClientFactory.getClient().users().getCurrentAccount().getRootInfo().toStringMultiline()).append("\n");
                    ToShow.append("Root : ").append(DropboxClientFactory.getClient().users().getCurrentAccount().getRootInfo().getRootNamespaceId()).append("\n");
                    ToShow.append("home : ").append(DropboxClientFactory.getClient().users().getCurrentAccount().getRootInfo().getHomeNamespaceId()).append("\n");
                    RootNameSpaceID = DropboxClientFactory.getClient().users().getCurrentAccount().getRootInfo().getRootNamespaceId();
                    DropboxFileNode Root = new DropboxFileNode("/RIVA",null);
                    Root.AddIndex(0);
                    TraversalQueue.add(Root);

                    DropboxFileNode tmp,tmpChild;
                    while(TraversalQueue.size()>0)
                    {
                        tmp = TraversalQueue.poll();
                        ListFolderResult result = DropboxClientFactory.getClient().withPathRoot(PathRoot.root(RootNameSpaceID)).files().listFolder(tmp.GetCurrentFolderName());
                        int IndexCounter = 0;
                        for(Metadata metadata : result.getEntries())
                        {
                            if(metadata instanceof FolderMetadata)
                            {
                                tmpChild = new DropboxFileNode(metadata.getPathDisplay(),tmp.GetCurrentFolderName());
                                tmpChild.SetIndexList(tmp.GetIndexList());
                                tmpChild.AddIndex(IndexCounter);
                                TraversalQueue.add(tmpChild);
                                TraversalArrayList.add(tmpChild);
                                IndexCounter++;
                            }
                        }
                    }
                    ArrayList<Integer> IndexLists;
                    DropboxFileNode tmpParent;

                    for(int i=0;i<TraversalArrayList.size();i++)
                    {
                        tmp = TraversalArrayList.get(i);
                        IndexLists = tmp.GetIndexList();
                        if(IndexLists.size()==1)
                        {
                            Root = tmp;
                        }
                        if(IndexLists.size() == 2)
                        {
                            Root.AddChildFolder(tmp.GetCurrentFolderName());
                        }
                        else
                        {
                            tmpParent = Root;
                            for(int j=1;j<IndexLists.size()-1;j++)
                            {
                                tmpParent = tmpParent.GetChildFolderArrayList().get(IndexLists.get(j));
                            }
                            tmpParent.AddChildFolder(tmp.GetCurrentFolderName());
                        }
                    }

                    for(int i=0;i<TraversalArrayList.size();i++)
                    {
                        ToShow.append("Current: ").append(TraversalArrayList.get(i).CurrentFolderName)
                                .append(TraversalArrayList.get(i).GetIndexListInString())
                                .append("\n");
                    }

                    ToShow.append("Showing\n");

                    TraversalQueue.add(Root);
                    while(TraversalQueue.size()>0)
                    {
                        tmp = TraversalQueue.poll();
                        TraversalQueue.addAll(tmp.GetChildFolderArrayList());
                        ToShow.append(tmp.GetCurrentFolderName()).append(",").append(tmp.GetIndexListInString()).append("\n");
                    }

                } catch (DbxException e) {
                    Log.e("DbxException",e.toString());
                }


                File tmpFile = null;
                try {
                    tmpFile = File.createTempFile("test","txt");
                    FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tmpFile));
                    String tmpString = "File for test function saving acceleration data\n" +
                            "Time Stamp at : " + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())) + "\n";
                    bufferedWriter.write(tmpString);
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IOException",e.toString());
                }
                tmpFile.delete();
                try (InputStream in = new FileInputStream(tmpFile)) {
                    String CurrentTimeString = "/RIVA/OSA_Record/" + String.valueOf(System.currentTimeMillis()) + ".txt";
                    FileMetadata metadata = DropboxClientFactory.getClient().withPathRoot(PathRoot.root(RootNameSpaceID)).files().uploadBuilder(CurrentTimeString)
                            .uploadAndFinish(in);
                    tmpFile.delete();
                } catch (UploadErrorException e) {
                    e.printStackTrace();
                    Log.e("UploadErrorException",e.toString());
                } catch (DbxException e) {
                    e.printStackTrace();
                    Log.e("DbxException",e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("IOException",e.toString());
                }

                runOnUiThread(()-> {
                    MessageTextView.setText(ToShow.toString());
                });*/
            });

            thread.start();


        }
    }

    private void SetLoginButton() {
        LoginButton.setOnClickListener((View v) -> {
            LoginRequested = true;
            //Auth.startOAuth2Authentication(MainActivity.this, "hoy2sk7ln87j09c");
            Auth.startOAuth2PKCE(MainActivity.this, "5hitpqdqmeyld28", DbxRequestConfigFactory.getRequestConfig(),
                    Arrays.asList("account_info.read", "files.content.write", "files.content.read", "sharing.read", "sharing.write"));
        });
    }
    private void SetShowDataButton() {
        ChooserButton.setOnClickListener((View v) -> {

        });
    }

    private class DropboxFileNode {

        private String CurrentFolderName;
        private String FolderParent;
        private ArrayList<DropboxFileNode> ChildFolderArrayList;
        private ArrayList<Integer> IndexArrayList;

        private DropboxFileNode(String FolderName,String Parent) {
            CurrentFolderName = FolderName;
            FolderParent = Parent;
            ChildFolderArrayList = new ArrayList<>();
            IndexArrayList = new ArrayList<>();
        }

        private void SetIndexList(ArrayList<Integer> Set){
            IndexArrayList.clear();
            IndexArrayList.addAll(Set);
        }

        private void AddIndex(int index) {
            IndexArrayList.add(index);
        }
        private ArrayList<Integer> GetIndexList() { return IndexArrayList; }
        private String GetIndexListInString() {
            StringBuilder result = new StringBuilder();
            for(int i=0;i<IndexArrayList.size();i++)
            {
                result.append(IndexArrayList.get(i));
                if(i!=IndexArrayList.size()-1)
                {
                    result.append(",");
                }
            }
            return result.toString();
        }

        private String GetCurrentFolderName() {
            return CurrentFolderName;
        }
        private String GetFolderParent() {
            return FolderParent;
        }

        private void AddChildFolder(String Name)
        {
            ChildFolderArrayList.add(new DropboxFileNode(Name,CurrentFolderName));
        }

        private ArrayList<DropboxFileNode> GetChildFolderArrayList() {
            return ChildFolderArrayList;
        }
    }

}