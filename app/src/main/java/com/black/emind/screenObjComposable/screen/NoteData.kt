package com.black.emind.screenObjComposable.screen

import com.black.emind.*

data class NoteData(val id: Int = NEW_ENTITY) {
    var name:       String  = getStringResource(R.string.new_note)
    var category:   Int     = DEFAULT_CATEGORY
    var text:       String  = ""
    var fontSize:   Int     = DEFAULT_FONTSIZE
 }