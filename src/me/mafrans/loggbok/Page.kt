package me.mafrans.loggbok

class Page(index: UShort, volume: Volume?) {
    var text: String = "";
    var index: UShort = index;
    val volume: Volume? = volume;
}