import { NgFor, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { UsuarioInicio } from '../usuario-inicio';
import { UsuarioService } from '../usuario.service';

@Component({
  selector: 'app-inicio',
  standalone: true,
  imports: [RouterModule, NgFor, NgIf],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent {
  usuario: UsuarioInicio | null = null;

  constructor(private usuarioService: UsuarioService) {
  }

  ngOnInit(): void {
    this.usuarioService.usuario$.subscribe({
      next: (usuario) => {
        this.usuario = usuario;
        console.log('Usuario inicial obtenido en AppComponent:', usuario);
      },
      error: (error) => {
        console.error('Error al obtener el usuario inicial en AppComponent:', error);
      }
    });
  }

}
