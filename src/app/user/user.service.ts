import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";


@Injectable()
export class UserService {
  constructor(private http: HttpClient) { }

  private baseURL = 'api/'

  public getAllUser(): Observable<any>{
    return this.http.get(`${this.baseURL}user/all`)
  }
}
