import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ListaCharacterComponent } from './lista-character/lista-character.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'characters', component: ListaCharacterComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
