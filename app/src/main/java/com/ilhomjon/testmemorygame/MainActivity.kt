package com.ilhomjon.testmemorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listImageOchiqYOpiq = arrayOf(false, false, false, false, false, false)
    var rasmIndex = arrayOfNulls<Int>(2)
    var rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_1.setOnClickListener {
            btnClick(image_1, R.drawable.moto_rasm, 0)
        }
        image_2.setOnClickListener {
            btnClick(image_2, R.drawable.moto_rasm, 1)
        }
    }

    fun btnClick(imageView: ImageView, rasm:Int, index:Int){
        if (listImageOchiqYOpiq[index] == false) {
            animationOchilishi(rasm, imageView, index)
            listImageOchiqYOpiq[index] = true
        } else {
            animationYopilishi(rasm, imageView, index)

        }
    }

    fun animationOchilishi(rasm:Int, imageView:ImageView, index: Int){
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(rasm)
                animation2.setAnimationListener(object :Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        println(ochiqRasm)
                        rasmIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++
                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {
                                imageViewAniqla(rasmIndex[0]).visibility = View.INVISIBLE
                                imageViewAniqla(rasmIndex[1]).visibility = View.INVISIBLE
                            } else {
                                animationYopilishi(-1, imageViewAniqla(rasmIndex[0]), rasmIndex[0])
                                animationYopilishi(-1, imageViewAniqla(rasmIndex[1]), rasmIndex[1])

                            }
                            rasmIndex[0] = null
                            rasmIndex[1] = null
                        }
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })

            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        imageView.startAnimation(animation)

    }

    fun animationYopilishi(rasm: Int, imageView: ImageView, index: Int?){
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.yulduzcha)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        imageView.startAnimation(animation)
         ochiqRasm--
        listImageOchiqYOpiq[index!!] = false
    }

    fun imageViewAniqla(index: Int?):ImageView{
        var imageView:ImageView? = null
        when(index){
            0 -> imageView = image_1
            1 -> imageView = image_2
            2 -> imageView = image_3
            3 -> imageView = image_4
            4 -> imageView = image_5
            5 -> imageView = image_6
        }
        return imageView!!
    }
}