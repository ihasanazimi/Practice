package ir.ha.practice.ui.tabs.components_tab.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import ir.ha.practice.R
import ir.ha.practice.databinding.FragmentNotificationBinding
import ir.ha.practice.ui.tabs.HostActivity
import ir.ha.practice.utility.base.BaseFragment
import ir.ha.practice.utility.extentions.checkPermission
import ir.ha.practice.utility.extentions.isOreoPlus
import ir.ha.practice.utility.extentions.isTIRAMISU

class NotificationFragment: BaseFragment<FragmentNotificationBinding>() {
    override val layoutId: Int get() = R.layout.fragment_notification

    private lateinit var notificationManager : NotificationManager
    private lateinit var simpleNotification : Notification
    private lateinit var bigPictureStyleNotification : Notification
    private lateinit var bigTextStyleNotification : Notification
    private lateinit var inboxStyleNotification : Notification
    private val channelId = "MYAPP"
    private val notificationID = 1002


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
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.drawable.logo))) // only -> (png - jpeg)
            .build()

        bigTextStyleNotification = NotificationCompat.Builder(requireContext(),channelId)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setContentText("bigTextStyleNotification")
            .setContentIntent(pendingIntentSample())
            .setStyle(NotificationCompat.BigTextStyle().bigText("----------------------------------------------------------------------"))
            .build()

        inboxStyleNotification = NotificationCompat.Builder(requireContext(),channelId)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("title")
            .setContentText("inboxStyleNotification")
            .setContentIntent(pendingIntentSample())
            .setStyle(NotificationCompat.InboxStyle().addLine("line 1 message").addLine("line 2 message").addLine("line 3 message"))
            .build()
    }


    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isTIRAMISU())
        checkPermission(requireActivity(),android.Manifest.permission.POST_NOTIFICATIONS,1256)

    }

    override fun clickEvents() {
        super.clickEvents()

        binding.btnSimpleNotification.setOnClickListener{ notificationManager.notify(notificationID,simpleNotification) }
        binding.btnBigPictureNotification.setOnClickListener{ notificationManager.notify(notificationID,bigPictureStyleNotification) }
        binding.btnBigTextNotification.setOnClickListener{ notificationManager.notify(notificationID,bigTextStyleNotification) }
        binding.btnInboxNotification.setOnClickListener{ notificationManager.notify(notificationID,inboxStyleNotification) }

    }

    private fun pendingIntentSample() : PendingIntent{
        val intent = Intent(requireActivity(), HostActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return getActivities(requireActivity(),0, arrayOf(intent), FLAG_UPDATE_CURRENT or FLAG_IMMUTABLE)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(channelId, "DEFAULT_CHANNEL", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "this is channel description"
        notificationManager.createNotificationChannel(channel)
    }
}