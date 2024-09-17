import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.css']
})
export class PaginatorComponent implements OnInit{
  @Input() totalSize: number;
  @Input() pageSize: number;
  totalPages = 0;
  currentPage = 1;
  pageSizeTemp = 5;
  index = 0;
  @Output() change = new EventEmitter<{}>();

  ngOnInit(): void {
    this.pageSizeTemp = this.pageSize;
    this.totalPages = Math.ceil(this.totalSize / this.pageSizeTemp);
    this.change.emit({ index: this.index, pageSize: this.pageSizeTemp});
  }

  onIncrease() {
    this.index++;
    this.index = this.index % this.totalPages;
    this.currentPage = this.index + 1;
    this.change.emit({ index: this.index, pageSize: this.pageSizeTemp });
  }
  
  onDecrease() {
    this.index--;
    this.index = (this.index + this.totalPages) % this.totalPages;
    this.currentPage = this.index + 1;
    this.change.emit({ index: this.index, pageSize: this.pageSizeTemp });
  }

  onEntry() {
    if(this.currentPage<=0 || this.currentPage>this.totalPages)
    {
      this.index = 0;
      this.change.emit({ index: this.index, pageSize: this.pageSizeTemp });
      return;  
    }
    this.index = this.currentPage - 1;
    this.change.emit({ index: this.index, pageSize: this.pageSizeTemp });
  }

  valueChange() {
    if(this.pageSizeTemp<=0){
      this.pageSizeTemp = this.totalSize;
    }
    if (this.pageSizeTemp>this.totalSize){
      this.pageSize = this.totalSize;
    }
    this.totalPages = Math.ceil(this.totalSize / this.pageSizeTemp);
    this.change.emit({ index: this.index, pageSize: this.pageSizeTemp });
  }
}
