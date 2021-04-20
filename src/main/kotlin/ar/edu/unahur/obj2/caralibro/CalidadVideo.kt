package ar.edu.unahur.obj2.caralibro

abstract class CalidadVideo {
    abstract fun tamanioVideo(): Int
}

class videoSd(val segundos : Int) : CalidadVideo() {
    override fun tamanioVideo() = segundos
}

open class videoHd720p(val segundos: Int) : CalidadVideo(){
    override fun tamanioVideo()= segundos * 3
}


class videoHd1080p (segundos: Int) : videoHd720p(segundos){
    override fun tamanioVideo()= super.tamanioVideo() * 2
}
