package com.example.customstartview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customstartview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private val binding by lazy { 
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // startIndicator
        binding.startIndicatorView.setOnClickListener { 
            var selected = binding.startIndicatorView.getSelected() // 선택된 별 가져오기
            if (selected == 2) {
                selected = 0 // 맨 오른쪽에 있을 때는 처음으로 돌아간다
            } else {
                selected++ // 1개씩 오른쪽으로 이동
            }
            binding.startIndicatorView.setSelected(selected) // 선택 상태를 갱신
        }
        
    }

}
