import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.ensoft.lesson7.R


class MainActivity : AppCompatActivity() {

    var backTime: Long = 0

    var questionNumber = 1
    var correctAnswers = 0

    /**O'yindagi savollar soni*/
    val numberOfQuestions = 7

    val images = Lists.images
    val options = Lists.options

    var correctAnswer = ""

    var generatedQuestions: MutableList<Int> = ArrayList()
    var generatedOptions: MutableList<Int> = ArrayList()

    val tv_question = findViewById<TextView>(R.id.tv_question)
    val tv_score = findViewById<TextView>(R.id.tv_score)
    val rb_1 = findViewById<RadioButton>(R.id.rb_1)
    val rb_2 = findViewById<RadioButton>(R.id.rb_2)
    val rb_3 = findViewById<RadioButton>(R.id.rb_3)
    val rb_4 = findViewById<RadioButton>(R.id.rb_4)
    val imageView = findViewById<ImageView>(R.id.imageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        generateQuestion()

        val btn_submit = findViewById<Button>(R.id.btn_submit)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        btn_submit.setOnClickListener {
            questionNumber++
            val id = radioGroup.checkedRadioButtonId

            when(id){
                R.id.rb_1 -> {
                    if (correctAnswer == rb_1.text.toString()){
                        showToast("Javob to'g'ri")
                        correctAnswers++
                    }
                    else
                        showToast("Javob noto'g'ri")
                }
                R.id.rb_2 -> {
                    if (correctAnswer == rb_2.text.toString()){
                        showToast("Javob to'g'ri")
                        correctAnswers++
                    }
                    else
                        showToast("Javob noto'g'ri")
                }
                R.id.rb_3 -> {
                    if (correctAnswer == rb_3.text.toString()){
                        showToast("Javob to'g'ri")
                        correctAnswers++
                    }
                    else
                        showToast("Javob noto'g'ri")
                }
                R.id.rb_4 -> {
                    if (correctAnswer == rb_4.text.toString()){
                        showToast("Javob to'g'ri")
                        correctAnswers++
                    }
                    else
                        showToast("Javob noto'g'ri")
                }
            }
            generateQuestion()

            /**O'yin tugash holati*/
            if (questionNumber > numberOfQuestions){
                tv_question.text = (questionNumber - 1).toString()
                gameOver()
            }
        }
    }


    fun generateQuestion(){
        //o'yinda 7 ta savol chiqadi
        if (questionNumber != numberOfQuestions + 1){
            rb_1.isChecked = true
            rb_2.isChecked = false
            rb_3.isChecked = false
            rb_4.isChecked = false

            /**Har yangi savolda variantlar listi bo'sh bo'lib boshlanishi uchun*/
            generatedOptions.clear()

            tv_question.text = questionNumber.toString()
            tv_score.text = correctAnswers.toString()

            /**Rasm*/
            var random = (images.indices).random()
            while (random in generatedQuestions){
                random = (images.indices).random()
            }
            generatedQuestions.add(random)
            imageView.setImageResource(images[random])
            correctAnswer = options[random]

            generatedOptions.add(random)

            val radioButtons = mutableListOf<Int>(
                R.id.rb_1,
                R.id.rb_2,
                R.id.rb_3,
                R.id.rb_4
            )

            /**To'g'ri javobni tasodifiy joylashtirish*/
            val randomLocation = (radioButtons.indices).random()
            val correctButton = findViewById<RadioButton>(radioButtons[randomLocation])
            correctButton.text = correctAnswer

            /**Noto'g'ri variantlarni generatsiya qilish*/
            for (i in radioButtons.indices){
                if (i == randomLocation)
                    continue

                val radioButton = findViewById<RadioButton>(radioButtons[i])

                random = (options.indices).random()

                while (random in generatedOptions){
                    random = (options.indices).random()
                }
                generatedOptions.add(random)
                /**Noto'g'ri variantlarni joylashtirish*/
                radioButton.text = options[random]
            }
        }

    }

    fun showToast(str: String){
        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }

    fun gameOver(){
        val intent = Intent(applicationContext, GameOverActivity::class.java)
        intent.putExtra("question", questionNumber -1)
        intent.putExtra("score", correctAnswers)
        startActivity(intent)
        finish()


    }

    override fun onBackPressed() {
        if (backTime +2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            Toast.makeText(applicationContext, "Chiqish uchun yana bosing", Toast.LENGTH_SHORT)
                .show()
        }
        backTime = System.currentTimeMillis()

    }


}