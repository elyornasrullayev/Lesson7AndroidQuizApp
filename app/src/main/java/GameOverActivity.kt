import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ensoft.lesson7.R

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val coorectAnswers = intent.getIntExtra("score", 0)
        val questions = intent.getIntExtra("question", 1)

        val tv_result = findViewById<TextView>(R.id.tv_result)
        tv_result.text = "Siz $questions ta savoldan $coorectAnswers topdingiz!"

        val btn_play_again = findViewById<Button>(R.id.btn_play_again)
        btn_play_again.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}