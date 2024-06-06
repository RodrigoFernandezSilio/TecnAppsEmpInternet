import { Component, Input } from '@angular/core';
import { SerieResumida } from '../serie';
import { UsuarioService } from '../usuario.service';
import { NgClass, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-catalogo-serie-item',
  standalone: true,
  imports: [NgFor, NgIf, NgClass],
  templateUrl: './catalogo-serie-item.component.html',
  styleUrl: './catalogo-serie-item.component.css'
})
export class CatalogoSerieItemComponent {
  @Input() serie!: SerieResumida; // Input para recibir la serie desde el componente padre
  @Input() destacada!: boolean; // Input para recibir si la serie esta destacada desde el componente padre

  serieAgregada: boolean = false;

  expanded: boolean = false;

  constructor(private usuarioService: UsuarioService) { }

  toggleExpand() {
    this.expanded = !this.expanded;
    console.log(`${this.expanded}`);
    console.log("Incono Expandir pulsado");
  }

  agregarSerie(serie: SerieResumida) {
    this.usuarioService.agregarSerieAEspacioPersonal(serie.id).subscribe();
    this.serieAgregada = true;
  }
}


