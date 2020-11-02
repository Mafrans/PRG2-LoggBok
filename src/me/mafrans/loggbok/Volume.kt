package me.mafrans.loggbok

class Volume {
    var index: UShort = 0u;
    var pages: Set<Page> = HashSet();

    constructor(index: UShort) {
        this.index = index;
    }
}