import { NgFor } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { SerieResumida } from '../serie';
import { SerieService } from '../serie.service';
import { UsuarioService } from '../usuario.service';
import { CatalogoSerieItemComponent } from '../catalogo-serie-item/catalogo-serie-item.component';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [RouterModule, RouterOutlet, NgFor, CatalogoSerieItemComponent],
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css'
})
export class CatalogoComponent {
  letrasYNumeros: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0-9'];

  series: SerieResumida[] = [];
  seriesDestacadas: boolean[] = [];

  letraNumeroActivo: string | null = null;

  @ViewChild('searchBox') searchBox!: ElementRef<HTMLInputElement>;

  constructor(private serieService: SerieService, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.buscarPorLetraNumero('L')
  }

  buscarPorLetraNumero(letraNumero: string) {
    if (letraNumero.match(/[0-9]/)) {
      this.serieService.obtenerSeriesQueEmpiezanPorNumero().subscribe(series => this.series = series);
    } else {
      this.serieService.obtenerSeries(letraNumero).subscribe(series => this.series = series);
    }
    this.letraNumeroActivo = letraNumero;
  }

  agregarSerie(serie: SerieResumida) {
    this.usuarioService.agregarSerieAEspacioPersonal(serie.id).subscribe();
  }

  search(term: string) {
    console.log("Buscar");
  }

  buscarPorNombre(nombreSerie: string) {
    // Obtener la primera letra del nombre de la serie
    const primeraLetra = nombreSerie.charAt(0);

    // Comprobar si la primera letra es una letra del alfabeto
    const esLetra = /^[A-Za-z]$/.test(primeraLetra);

    // Comprobar si la primera letra es un número del 0 al 9
    const esNumero = /^[0-9]$/.test(primeraLetra);

    if (esLetra) {
      this.serieService.obtenerSeries(primeraLetra).subscribe(serie => {
        this.series = serie;
        this.seriesDestacadas = this.series.map(serie => serie.nombre === nombreSerie);
        this.letraNumeroActivo = primeraLetra;
      });
      this.letraNumeroActivo = primeraLetra;
    } else if (esNumero) {
      this.serieService.obtenerSeriesQueEmpiezanPorNumero().subscribe(serie => {
        this.series = serie;
        this.seriesDestacadas = this.series.map(serie => serie.nombre === nombreSerie);
        this.letraNumeroActivo = "0-9";
      });
    } else {
      console.log("No es ni letra ni número:", primeraLetra);
      return;
    }
  }


  verMas(serie: SerieResumida) {

  }


}
