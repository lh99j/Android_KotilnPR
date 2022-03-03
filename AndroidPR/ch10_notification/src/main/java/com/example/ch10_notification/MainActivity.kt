package com.example.ch10_notification

import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.ch10_notification.databinding.ActivityMainBinding
import com.example.ch10_notification.databinding.DialogInputBinding
import android.os.Vibrator
import android.os.VibrationEffect

import android.os.Build
import androidx.core.app.NotificationCompat
import android.app.PendingIntent
import android.content.BroadcastReceiver


class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                    Log.d("kkang", "teat : $p1, month : ${p2 + 1}, dayOfMonth : $p3")
                }
            },2020, 8, 21).show()
        }

        binding.btn2.setOnClickListener {
            AlertDialog.Builder(this).run{
                setTitle("test dialog")
                setMessage("정말 종료하시겠습니까?")
                setIcon(android.R.drawable.ic_dialog_info)
                setPositiveButton("OK", null)
                setNegativeButton("Cancel", null)
                setNeutralButton("More", null)
                setPositiveButton("Yes", null)
                setNegativeButton("No", null)
                show()
            }
        }

        binding.btn3.setOnClickListener {
            val eventHandler = object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if(p1 == DialogInterface.BUTTON_POSITIVE){
                        Log.d("kkang", "positive button click")
                    }else if(p1 == DialogInterface.BUTTON_NEGATIVE){
                        Log.d("kkang", "negative button click")
                    }
                }
            }
            AlertDialog.Builder(this).run{
                setTitle("test dialog")
                setMessage("정말 종료하시겠습니까?")
                setIcon(android.R.drawable.ic_dialog_info)
                setNeutralButton("More", null)
                setPositiveButton("Yes", eventHandler)
                setNegativeButton("No", eventHandler)
                show()
            }
        }

        binding.btn4.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run{
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("kkang", "선택한 과일 : ${items[p1]}")
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.btn5.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run{
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setMultiChoiceItems(items, booleanArrayOf(true, false, true, false), object : DialogInterface.OnMultiChoiceClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                        Log.d("kkang", "${items[p1]} 이 ${if(p2) "선택 되었습니다." else "선택 해제되었습니다."}")
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.btn6.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run{
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 1, object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("kkang", "${items[p1]} 이 선택되었습니다.")
                    }
                })
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.btn7.setOnClickListener {
            val items = arrayOf<String>("사과", "복숭아", "수박", "딸기")
            AlertDialog.Builder(this).run{
                setTitle("items test")
                setIcon(android.R.drawable.ic_dialog_info)
                setItems(items, object : DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Log.d("kkang", "선택한 과일 : ${items[p1]}")
                    }
                })
                setCancelable(false)
                setPositiveButton("닫기", null)
                show()
            }.setCanceledOnTouchOutside(false)
        }

        binding.btn8.setOnClickListener {
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)

            AlertDialog.Builder(this).run{
                setTitle("Input")
                setView(dialogBinding.root)
                setPositiveButton("닫기", null)
                show()
            }
        }

        binding.btn9.setOnClickListener {
            val notification : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
            ringtone.play()
        }

        binding.btn10.setOnClickListener {
            val vibrator = if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
                val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            }else {
                getSystemService(VIBRATOR_SERVICE) as Vibrator
            }

//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                vibrator.vibrate(
//                    VibrationEffect.createOneShot(500,
//                        VibrationEffect.DEFAULT_AMPLITUDE))
//            }else{
//                vibrator.vibrate(500)
//            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                vibrator.vibrate(VibrationEffect.createWaveform(longArrayOf(500, 1000, 500, 2000),
                intArrayOf(0, 50, 0, 200), -1))
            }else{
                vibrator.vibrate(longArrayOf(500, 1000, 500, 2000), -1)
            }
        }

        binding.btn11.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelId = "one-channel"
                val channelName = "My channel One"
                val channel = NotificationChannel(
                    channelId, channelName, NotificationManager.IMPORTANCE_HIGH
                )

                //채녈에 다양한 정보 설정
                channel.description = "My channel One Description"
                channel.setShowBadge(true)
                val uri:Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                channel.lightColor = Color.RED
                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)

                //채널을 notificationManager에 등록
                manager.createNotificationChannel(channel)

                //채널을 이용해 빌더 생성
                builder = NotificationCompat.Builder(this, channelId)


                builder.setSmallIcon(R.drawable.send) //아이콘
                builder.setWhen(System.currentTimeMillis()) //발생 시각
                builder.setContentTitle("Content Title") //제목
                builder.setContentText("Content Message") //내용


                //알림 클릭시 activity_main으로 이동`
                val intent = Intent(this, DetailActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(this, 10, intent,
                PendingIntent.FLAG_IMMUTABLE)

                builder.setContentIntent(pendingIntent)

                val actionIntent = Intent(this, DetailActivity::class.java)
                val actionPendingIntent = PendingIntent.getBroadcast(this, 20, actionIntent, PendingIntent.FLAG_IMMUTABLE)

                builder.addAction(
                    NotificationCompat.Action.Builder(
                        android.R.drawable.stat_notify_more,
                        "Action",
                        actionPendingIntent
                    ).build()
                )

                manager.notify(11, builder.build())

//                val KEY_TEXT_REPLY = "key_text_reply"
//                var replyLabel: String = "답장"
//                var remoteInput : RemoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run{
//                    setLabel(replyLabel)
//                    build()
//                }
//
//                val replyIntent = Intent(this, ReplyReceiver::class.java)
//                val replyPendingIntent = PendingIntent.getBroadcast(applicationContext, 30, replyIntent, PendingIntent.FLAG_MUTABLE)
//
//                builder.addAction(
//                    NotificationCompat.Action.Builder(
//                        R.drawable.send,
//                        "답장",
//                        replyPendingIntent
//                    ).addRemoteInput(remoteInput).build()
//                )
//
//                val replyTxt = getResultsFromIntent(intent)
//                    ?.getCharSequence("key_text_reply")
//
//                manager.notify(11, builder.build())

            }else{
                builder = NotificationCompat.Builder(this)
            }
        }

    }
}