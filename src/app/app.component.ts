import {Component, OnInit} from '@angular/core';
import axios from 'axios';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'pipeline azure';
  employees = ''
  ngOnInit(): void {
    axios.get('api/employees')
      .then((response) => {
        // handle success
        this.employees = response.data
        console.log(response.data);
      })
      .catch(function (error) {
        // handle error
        console.log(error);
      })
      .finally(function () {
        // always executed
      });
  }

}
