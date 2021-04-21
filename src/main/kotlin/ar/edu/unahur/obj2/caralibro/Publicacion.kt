package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil

abstract class Publicacion {
  var cantidadDeMeGustaDeLaPublicacion = Int
  abstract fun espacioQueOcupa(): Int


  fun recibeMeGUsta() {
    cantidadDeMeGustaDeLaPublicacion += 1
  }




}

class Foto(val alto: Int, val ancho: Int) : Publicacion() {
  val factorDeCompresion = 0.7
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
}

class Texto(val contenido: String) : Publicacion() {
  override fun espacioQueOcupa() = contenido.length
}

class Video(val calidadDeVideo: CalidadVideo) : Publicacion(){
  override fun espacioQueOcupa() = calidadDeVideo.tamanioVideo()
}


