package com.example.phonetimer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.phonetimer.databinding.ItemUsageBinding
import java.util.concurrent.TimeUnit

class UsageListAdapter : RecyclerView.Adapter<UsageListAdapter.UsageViewHolder>() {

    private var usageList: List<AppUsageInfo> = emptyList()

    fun submitList(newList: List<AppUsageInfo>) {
        usageList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsageViewHolder {
        val binding = ItemUsageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsageViewHolder, position: Int) {
        holder.bind(usageList[position])
    }

    override fun getItemCount(): Int = usageList.size

    class UsageViewHolder(private val binding: ItemUsageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(info: AppUsageInfo) {
            binding.appName.text = info.appName
            binding.packageName.text = info.packageName
            binding.appIcon.setImageDrawable(info.appIcon)
            binding.usageTime.text = formatTime(info.usageTimeMillis)
        }

        private fun formatTime(millis: Long): String {
            val hours = TimeUnit.MILLISECONDS.toHours(millis)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60
            return when {
                hours > 0 -> String.format("%dh %dm", hours, minutes)
                minutes > 0 -> String.format("%dm %ds", minutes, seconds)
                else -> String.format("%ds", seconds)
            }
        }
    }
}
