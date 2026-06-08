import { Component, computed, input, output, signal } from '@angular/core';
import { ChallangeModel } from '../../core/model/challange.model';

@Component({
  selector: 'app-challange',
  imports: [],
  templateUrl: './challange.html',
  styleUrl: './challange.css',
})
export class Challange {

events = input.required<ChallangeModel[]>();
  selectEvent = output<ChallangeModel>();
  
  selectedFilter = signal<'ALL' | 'UPCOMING' | 'ACTIVE' | 'FINISHED' | 'CANCELLED'>('ALL');
  filters = ['ALL', 'UPCOMING', 'ACTIVE', 'FINISHED', 'CANCELLED'] as const;
  
  filteredEvents = computed(() => {
    const filter = this.selectedFilter();
    const allEvents = this.events();
    
    if (filter === 'ALL') return allEvents;
    return allEvents.filter(event => event.status === filter);
  });
  
  getStatusLabel(status: string): string {
    const labels: Record<string, string> = {
      'UPCOMING': 'Nadchodzące',
      'ACTIVE': 'Aktywne',
      'FINISHED': 'Zakończone',
      'CANCELLED': 'Odwołane'
    };
    return labels[status] || status;
  }
}
