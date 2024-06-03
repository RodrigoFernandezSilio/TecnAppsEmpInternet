import { NgFor } from '@angular/common';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { SerieResumida } from '../serie';
import { SerieService } from '../serie.service';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-catalogo',
  standalone: true,
  imports: [RouterModule, RouterOutlet, NgFor],
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css'
})
export class CatalogoComponent {
  letrasYNumeros : string[] = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
  'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0-9'];

  serie: SerieResumida[] = [];
  
  @ViewChild('searchBox') searchBox!: ElementRef<HTMLInputElement>;

  constructor(private serieService: SerieService, private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.seleccionarLetra('J')
  }

  seleccionarLetra(letra: string) {
    if (letra.match(/[0-9]/)) {
      this.serieService.obtenerSeriesQueEmpiezanPorNumero().subscribe(serie => this.serie = serie);
    } else {
      this.serieService.obtenerSeries(letra).subscribe(serie => this.serie = serie);
    }
  }

  agregarSerie(serie: SerieResumida) {
    this.usuarioService.agregarSerieAEspacioPersonal(serie.id).subscribe();
  }

  search(term: string) {
    console.log("Buscar");
  }

  buscar() {
    this.search(this.searchBox.nativeElement.value);
  }


}
