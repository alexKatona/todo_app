package sk.alex_katona.todo_app

import android.os.Bundle
import sk.alex_katona.todo_app.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}