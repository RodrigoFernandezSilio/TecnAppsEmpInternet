import { NgFor, NgIf } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { SerieResumida } from '../serie';
import { SerieService } from '../serie.service';
import { UsuarioService } from '../usuario.service';
import { CatalogoSerieItemComponent } from '../catalogo-serie-item/catalogo-serie-item.component';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [RouterModule, RouterOutlet, NgFor, NgIf, CatalogoSerieItemComponent],
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css'
})
export class CatalogoComponent {
  letrasYNumeros: string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0-9'];

  series: SerieResumida[] = [];
  seriesDestacadas: boolean[] = [];
  busquedaExitosa: boolean = true;

  letraNumeroActivo: string | null = null;

  @ViewChild('searchBox') searchBox!: ElementRef<HTMLInputElement>;

  constructor(private serieService: SerieService, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.buscarPorLetraNumero('A')
  }

  buscarPorLetraNumero(letraNumero: string) {
    if (letraNumero.match(/[0-9]/)) {
      this.serieService.obtenerSeriesQueEmpiezanPorNumero().subscribe(series => {
        this.series = series;
        this.ordenarSeriesAlfabeticamente();
      });
    } else {
      this.serieService.obtenerSeries(letraNumero).subscribe(series => {
        this.series = series;
        this.ordenarSeriesAlfabeticamente();
      });
    }
    this.letraNumeroActivo = letraNumero;
    this.seriesDestacadas = this.seriesDestacadas.map(v => false);
    this.busquedaExitosa = true;
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
      this.serieService.obtenerSeries(primeraLetra).subscribe(series => {
        this.series = series;
        this.ordenarSeriesAlfabeticamente();
        this.seriesDestacadas = this.series.map(serie => serie.nombre === nombreSerie);
        this.letraNumeroActivo = primeraLetra;
        this.busquedaExitosa = this.seriesDestacadas.includes(true);
      });
    } else if (esNumero) {
      this.serieService.obtenerSeriesQueEmpiezanPorNumero().subscribe(series => {
        this.series = series;
        this.ordenarSeriesAlfabeticamente();
        this.seriesDestacadas = this.series.map(serie => serie.nombre === nombreSerie);
        this.letraNumeroActivo = "0-9";
        this.busquedaExitosa = this.seriesDestacadas.includes(true);
      });
    } else {
      console.log("No es ni letra ni número:", primeraLetra);
      return;
    }
  }

  // Función para ordenar this.series alfabéticamente por el nombre de la serie
  ordenarSeriesAlfabeticamente() {
    this.series.sort((a, b) => {
      return a.nombre.localeCompare(b.nombre);
    });
  }
}
