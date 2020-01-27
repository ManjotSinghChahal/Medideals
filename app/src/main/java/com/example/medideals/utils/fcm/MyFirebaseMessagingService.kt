package com.example.medideals.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.medideals.R
import com.example.medideals.ui.activities.home.HomeActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val channelId = ""
    private val notificationId = 0


    override fun onNewToken(s: String) {
        super.onNewToken(s)
        SharedPrefUtil.getInstance()!!.saveDeviceToken(s)
        Log.e("refreshedToken", "Refreshed_token: " + s!!)
    }



    //   appdeveloper00013@gmail.com   passProject  firebase noti


    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)



        if (SharedPrefUtil.getInstance()!!.getNotification()!!)
        {
            // show notif
        }
        else
        {

        }








/*
        //  if back end send json in notification then automatically displayed not in on MessageReceived
        // if back end send json in data then onMessageReceived get called

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        Log.e("notificationFetch", "notification: got it!!!! ")
        Log.e("notificationFetch", "notificationFetch" + remoteMessage!!.data)
        Log.e("notificationFetch",remoteMessage.data.toString())
        Log.e("notificationFetch->",remoteMessage.from)


*//*        // Check if
        // message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification ContactBody: " + remoteMessage.notification!!.body!!)
        }*//*





        try {
            val jsonOuter = JSONObject()
            val jsonInner = jsonOuter.getJSONObject("data")
            Log.e("tgttg",jsonInner.toString())

             val from_lat = jsonInner.getString("from_lat")
             val from_long = jsonInner.getString("from_long")
             val to_lat = jsonInner.getString("to_lat")
             val to_long = jsonInner.getString("to_long")
             val amount = jsonInner.getString("amount")
             val trip_id = jsonInner.getString("trip_id")
             val user_id = jsonInner.getString("user_id")
             val type = jsonInner.getString("type")
             val title = jsonInner.getString("title")


            sendNotification(from_lat,from_long,to_lat,to_long,amount,trip_id,user_id,type,title)

           *//* val jsonObject = JSONObject(remoteMessage.data["notification_code"])
            val jsonObject2 = jsonObject.getJSONObject("data")*//*


        } catch (ex: Exception) {
           // Log.e("notification","this in notification " + ex.localizedMessage)
        }*/

    }


    private fun sendNotification(from_lat : String,from_long : String,to_lat : String,to_long : String,amount : String,trip_id : String,user_id : String,type : String,title : String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("",type)

      //  val bundle = Bundle()
        intent.putExtra("from_lat", from_lat)
        intent.putExtra("from_long", from_long)
        intent.putExtra("to_lat", to_lat)
        intent.putExtra("to_long", to_long)
        intent.putExtra("amount", amount)
        intent.putExtra("trip_id", trip_id)
        intent.putExtra("user_id", user_id)



        val NOTIFICATION_CHANNEL_ID = "channel_id"
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.action = java.lang.Long.toString(System.currentTimeMillis())
        val bitmap: Bitmap? = null
        //        ToastUtils.shortToast("sendNotification");
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_background)
            //                Bitmap bitmap;
            //                .setLargeIcon(bitmap)
            .setContentTitle(title).setContentText(title).setAutoCancel(true).setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         //   var mChannel: NotificationChannel? = null
            var   mChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                getString(R.string.app_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            // Configure the notification channel.
            mChannel.description = title
            mChannel.enableLights(true)
          //  mChannel.setShowBadge(true)
            mChannel.lightColor = ContextCompat.getColor(this, R.color.colorPrimary)
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager?.createNotificationChannel(mChannel)
        } else {
            notificationBuilder.setVibrate(longArrayOf(100, 250))
                .setLights(ContextCompat.getColor(this, R.color.colorPrimary), 500, 5000)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
        }
        notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
        notificationManager.notify(notificationId, notificationBuilder.build())

    }








    /*
     *To get a Bitmap image from the URL received
     */

    fun getBitmapfromUrl(imageUrl: String): Bitmap? {
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
            return null
        }

    }

    //    private Bitmap getCircleBitmap(Bitmap bitmap) {
    //        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
    //                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    //        final Canvas canvas = new Canvas(output);
    //
    //        final int color = Color.RED;
    //        final Paint paint = new Paint();
    //        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    //        final RectF rectF = new RectF(rect);
    //
    //        paint.setAntiAlias(true);
    //        canvas.drawARGB(0, 0, 0, 0);
    //        paint.setColor(color);
    //        canvas.drawOval(rectF, paint);
    //
    //        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    //        canvas.drawBitmap(bitmap, rect, rect, paint);
    //
    //        bitmap.recycle();
    //
    //        return output;
    //    }

    private fun getCircleBitmap(bitmap: Bitmap): Bitmap {
        val output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val color = Color.RED
        val paint = Paint()
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        val rectF = RectF(rect)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        val cx = (bitmap.width / 2).toFloat()
        val cy = (bitmap.height / 2).toFloat()
        val radius = if (cx < cy) cx else cy
        canvas.drawCircle(cx, cy, radius, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        bitmap.recycle()
        return output
    }

    companion object {

        val NOTIFY_MESSAGE = "msg_key"
        val USER_IMAGE = "user_avatar"
        val USER_NAME = "my_key"
        val TITLE = "title"
        val BOOKING_ID = "booking_id"
        val NOTIFY_KEY = "notify_key"
        val ACCEPT_REJECT = "Accept Reject"
        val BOOKING_COMPLETED = "Booking Complete"

        private// TODO need to add other icon
        val notificationIcon: Int
            get() {
                val whiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
                return if (whiteIcon) R.drawable.ic_launcher_background else R.drawable.ic_launcher_background
            }
    }


    //    private Bitmap getCircleBitmap(Bitmap bitmap) {
    //        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    //        final Canvas canvas = new Canvas(output);
    //        final int color = Color.RED;
    //        final Paint paint = new Paint();
    //        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    //        final RectF rectF = new RectF(rect);
    //
    //        paint.setAntiAlias(true);
    //        canvas.drawARGB(0, 0, 0, 0);
    //        paint.setColor(color);
    //        canvas.drawOval(rectF, paint);
    //
    //        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    //        canvas.drawBitmap(bitmap, rect, rect, paint);
    //
    //        bitmap.recycle();
    //
    //        return output;
    //    }


    fun sendBroadCastUpdateList()
    {
       /* val intent = Intent("ACTION_List_UPDATE_BROADCAST")
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)*/
    }





}
