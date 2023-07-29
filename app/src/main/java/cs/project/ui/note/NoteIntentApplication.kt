package cs.project.ui.note

import android.app.Application

class NoteIntentApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}