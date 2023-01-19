import { CharacterService } from '../core/services/character.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-lista-character',
  templateUrl: './lista-character.component.html',
  styleUrls: ['./lista-character.component.css']
})
export class ListaCharacterComponent implements OnInit {

  characters: any[] = [];
  totalPages = 0;
  totalElements = 0;
  pageNumber = 0;
  pageSize = 10;

  pageIndexes: Array<number> = [];

  name = '';

  constructor(private service: CharacterService) {

  }

  ngOnInit() {
    this.getAll(this.pageNumber);
  }

  getAll(pageNumber: number) {
    this.service.getPageable(pageNumber, this.pageSize,).subscribe(
      (data) => {
        this.characters = data.content;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
        this.pageNumber = data.number;
        this.pageIndexes = Array(this.totalPages)
          .fill(0)
          .map((x, i) => i);
      },
      err => console.log(err)
    );
  }

  previousClick() {
    if (this.pageNumber > 0) {
      this.getAll(--this.pageNumber);
    }
  }

  nextClick() {
    if (this.pageNumber < this.totalPages - 1) {
      this.getAll(++this.pageNumber);
    }
  }

  //  active(index: number) {
  //   if (this.pageNumber === index) {
  //     return {
  //       active: true,
  //     };
  //   }
  // }

  pageClick(page: number) {
    this.getAll(page);
  }

  onChange(value: string) {
    this.name = value;
  }

  paginate(event: { page: number; }) {
    this.getAll(event.page)
  }

}