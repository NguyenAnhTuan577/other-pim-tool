import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class Util {
    public params = {
        'language': 'en',
        'input': '',
        'status': ''
    }; 

    getParams() {
        let params = {};
        if (this.params.input) {
            params['input'] = this.params.input;
        }
        if (this.params.status) {
            params['status'] = this.params.status;
        }
        if (this.params.language !== 'en') {
            params['language'] = this.params.language;
        }
        return params;
    }
}