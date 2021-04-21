package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class

UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoEnElSur = Video(videoSd(150))
    val videoEnElNorte = Video(videoHd720p(100))
    val videoEnElOeste = Video(videoHd1080p(120))


    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
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
      }

    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
      }
    }
  }


})
