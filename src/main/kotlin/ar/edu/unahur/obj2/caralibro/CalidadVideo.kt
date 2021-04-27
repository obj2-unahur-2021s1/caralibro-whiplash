package ar.edu.unahur.obj2.caralibro

abstract class CalidadVideo(val segundos: Int){
    abstract fun tamanioVideo(): Int

}

class videoSd(segundos: Int) : CalidadVideo(segundos) {
    override fun tamanioVideo() = segundos

}

open class videoHd720p(segundos: Int) : CalidadVideo(segundos){
    override fun tamanioVideo()= segundos * 3
}


class videoHd1080p (segundos: Int) : videoHd720p(segundos){
    override fun tamanioVideo()= super.tamanioVideo() * 2
}
