package jp.techacademy.madoka.inagaki.contentprovider

import android.content.ContentUris
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log

class MainActivity : AppCompatActivity() {

//    private val PERMISSIONS_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        getContentsInfo()
////
//////        //Android6.0のバージョンコードがＭだからＭ以上の場合は確認が必要
//////        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
////
//////        }else{
//////            //android5以下なら実行へ
//////            getContentsInfo()
//////        }
////    }
////
////    private fun getContentsInfo(){
////        //画像情報の取得
        val resolver = contentResolver
        //contentResoluverクラスのqueryメソッドは条件を指定して検索し情報を取得できる。
        var cursor = resolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, //データの種類
            null,
            null,
            null,
            null
        )

        //cursorはデータベース上の検索結果を格納するもの。
        //最初にtofirts,次からはtonext。データがあればtrue無ければfalseを返す
        if (cursor!!.moveToFirst()){
            do{
                //今cursorがさしているデータの、画像idがセットされている位置を取得
                val fieldIndex = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                //その位置をgetLongで取得
                val id = cursor.getLong(fieldIndex)
                //uriを取得してログに出力
                val imageUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id)

                Log.d("test","URI : "+ imageUri.toString())
            }while (cursor.moveToNext())
        }
        //データがなくfalseが返ったらifには入らずこっちのcloseへ来る
        cursor.close()
    }
}