import { Generic } from './generic.model';
import { Employee } from './employee.model';
import { Group } from './group.model';
export class Project extends Generic {
    public projectNumber: number;
    public name: string;
    public customer: string;
    public status: string;
    public startDate: Date;
    public endDate: Date;
    public group: Group;
    public projectEmployees: Employee[];
}