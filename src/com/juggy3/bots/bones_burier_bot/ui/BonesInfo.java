package com.juggy3.bots.bones_burier_bot.ui;

public class BonesInfo
{
    public int bonesPerHour, xpPerHour, prayerLvlsGained;
    public String timeRunning, currentTask;

    public BonesInfo()
    {
        this.bonesPerHour = 0;
        this.xpPerHour = 0;
        this.prayerLvlsGained = 0;
        this.timeRunning = "";
        this.currentTask = "";
    }

    public BonesInfo(int bonesPerHour, int xpPerHour, int prayerLvlsGained, String timeRunning, String currentTask)
    {
        this.bonesPerHour = bonesPerHour;
        this.xpPerHour = xpPerHour;
        this.prayerLvlsGained = prayerLvlsGained;
        this.timeRunning = timeRunning;
        this.currentTask = currentTask;
    }
}
