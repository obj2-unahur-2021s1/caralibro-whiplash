package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class

UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val fotoEnEsquel = Foto(800,1000)
    val videoEnElSur = Video(videoSd(150))
    val videoEnElNorte = Video(videoHd720p(100))
    val videoEnElOeste = Video(videoHd1080p(120))
    /*
    videoEnElSur.setCalidad(videoSd)
    videoEnElSur.setTiempo(seg)
    videoEnElSur.espacioQueOcupa()
    videoEnElSur.setCalidad(videoHd1080p)
    videoEnElSur.espacioQueOcupa()
     */
    //videoEnElSur.setCalidad(videoSd(150))
    //videoEnElNorte.setCalidad(videoHd720p(100))
    //videoEnElOeste.setCalidad(videoHd1080p(120))
    val fotoEnAconcagua = Foto(400,600)

    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
        it("ocupa ancho * alto * compresion bytes de foto en esquel"){
          fotoEnEsquel.espacioQueOcupa().shouldBe(560000)
        }
        it("ocupa ancho * alto * compresion bytes de foto en aconcagua"){
          fotoEnAconcagua.espacioQueOcupa().shouldBe(168000)
        }

      }

      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud") {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
        }
      }

      describe("de tipo de video"){
        it("tipo de video sd ocupa tantos bytes como su duracion en segundos"){
          videoEnElSur.espacioQueOcupa().shouldBe(150)
        }
        it("tipo de video hd720p es el triple de su duracion en seg"){
          videoEnElNorte.espacioQueOcupa().shouldBe(300)
        }
        it("tipo de video hd1080p es el doble de 720p"){
          videoEnElOeste.espacioQueOcupa().shouldBe(720)
        }
        it("cambia la calidad de video de una publicacion"){
          //videoEnElNorte.setCalidad(videoSd)
        }
      }

    }

    describe("Un usuario") {

      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(fotoEnAconcagua)
        juana.agregarPublicacion(fotoEnEsquel)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(1278548)
      }

    describe("Probamos como los usuarios dan like"){
      val pepe = Usuario()
      val jose = Usuario()
      val luis = Usuario()
      val juanito = Usuario()
      pepe.agregarPublicacion(fotoEnAconcagua)
      jose.darMegusta(fotoEnAconcagua)
      luis.darMegusta(fotoEnAconcagua)

      it("dos usuarios dan me gusta a publicacion"){
        fotoEnAconcagua.cantidadDeMeGustaDeLaPublicacion.shouldBe(2)
        fotoEnAconcagua.usuariosQueDieronLikeALaPublicacion.shouldContainExactly(jose,luis)
      }

      it("usuario no puede dar like a una publicacion porque ya dio like"){
        shouldThrowAny { jose.darMegusta(fotoEnAconcagua) }
      }
    }

    }
  }


})
