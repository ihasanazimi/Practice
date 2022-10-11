package ir.ha.dummy.ui.fragment

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import ir.ha.dummy.R
import ir.ha.dummy.databinding.FragmentNotificationBinding
import ir.ha.dummy.ui.MainActivity
import ir.ha.dummy.utility.base.BaseFragment
import ir.ha.dummy.utility.extentions.isOreoPlus

class NotificationFrg: BaseFragment<FragmentNotificationBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notification
    private lateinit var notificationManager : NotificationManager

    private lateinit var simpleNotification : Notification
    private lateinit var bigPictureStyleNotification : Notification
    private lateinit var bigTextStyleNotification : Notification
    private lateinit var inboxStyleNotification : Notification
    private val channelId = "MYAPP"

    override fun onAttach(context: Context) {
        super.onAttach(context)

        notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // if sdk Integer was bigger than oreo -> create notification channel
        if (isOreoPlus()) createNotificationChannel()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        simpleNotification = NotificationCompat.Builder(requireContext(),channelId)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setContentText("simpleNotification")
            .setContentIntent(pendingIntentSample())
            .build()

        bigPictureStyleNotification = NotificationCompat.Builder(requireContext(),channelId)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setContentText("bigPictureStyleNotification")
            .setContentIntent(pendingIntentSample())
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(resources, R.drawable.logo)) // only -> (png - jpeg)
            )
            .build()

        bigTextStyleNotification = NotificationCompat.Builder(requireContext(),channelId)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setContentText("bigTextStyleNotification")
            .setContentIntent(pendingIntentSample())
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("hasanaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            )
            .build()

        inboxStyleNotification = NotificationCompat.Builder(requireContext(),channelId)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setContentText("inboxStyleNotification")
            .setContentIntent(pendingIntentSample())
            .setStyle(
                NotificationCompat.InboxStyle().addLine("line 1 message").addLine("line 2 message")
                    .addLine("line 3 message")
            )
            .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSimpleNotification.setOnClickListener{
            notificationManager.notify(1002,simpleNotification)
        }

        binding.btnBigPictureNotification.setOnClickListener{
            notificationManager.notify(1002,bigPictureStyleNotification)
        }

        binding.btnBigTextNotification.setOnClickListener{
            notificationManager.notify(1002,bigTextStyleNotification)
        }

        binding.btnInboxNotification.setOnClickListener{
            notificationManager.notify(1002,inboxStyleNotification)
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun pendingIntentSample() : PendingIntent{
        val intent = Intent(requireActivity(),MainActivity::class.java)
        intent.addFlags(FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
        return getActivities(requireActivity(),0, arrayOf(intent),
            FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
    }
    private fun createNotificationChannel() {
        val channel = NotificationChannel(channelId, "DEFAULT_CHANNEL", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "this is channel description"
        notificationManager.createNotificationChannel(channel)
    }
}