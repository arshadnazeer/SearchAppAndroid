package in.arshad.searchapp;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatImageView;
import in.arshad.searchapp.database.SqliteController;
import in.arshad.searchapp.search.SessionData;
import in.arshad.ui.extensions.ViewExtKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

  ImageView imgBack, imgItem;
  TextView txtTitle;
  Button btnSubmit;
  EditText edtComment;
  String comment;
  ListView commentListView;
  BaseAdapter adapter;

  SqliteController controller = new SqliteController(this);
  ArrayList<HashMap<String, String>> saveCommentList = new ArrayList<HashMap<String, String>>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    imgBack = (ImageView) findViewById(R.id.imageBack);
    imgItem = (ImageView) findViewById(R.id.imgItem);
    txtTitle = (TextView) findViewById(R.id.textHeader);
    btnSubmit = (Button) findViewById(R.id.btnSubmit);
    edtComment = (EditText) findViewById(R.id.edtComment);
    commentListView = (ListView) findViewById(R.id.list);

    txtTitle.setText(Objects.requireNonNull(SessionData.INSTANCE.getItemData()).getTitle());
    loadImage();

    setAdapter();

    imgBack.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    btnSubmit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        comment = edtComment.getText().toString().trim();
        if (comment.length() > 0) {
          HashMap<String, String> commentsMap = new HashMap<String, String>();
          commentsMap.put("Comment", comment);
          commentsMap.put("ImageId", SessionData.INSTANCE.getItemData().getId());
          controller.insertComment(commentsMap);
          saveCommentList.add(commentsMap);
          ((BaseAdapter)adapter).notifyDataSetChanged();
          Toast.makeText(MainActivity.this, "Comment Added Successfully", Toast.LENGTH_SHORT)
              .show();
          edtComment.setText("");
        } else {
          Toast.makeText(MainActivity.this, "Enter comment", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

  public void loadImage() {
    ViewExtKt.loadURL((AppCompatImageView) imgItem,
        Objects.requireNonNull(SessionData.INSTANCE.getItemData().getImage()));
  }

  public void setAdapter() {
    saveCommentList = controller
        .getCommentInfo(Objects.requireNonNull(SessionData.INSTANCE.getItemData()).getId());
    adapter = new SimpleAdapter( MainActivity.this,saveCommentList, R.layout.item_comment, new String[] {"Comment"}, new int[] {R.id.txtComment});
    commentListView.setAdapter(adapter);
  }
}
