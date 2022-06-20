/* globals Chart:false, feather:false */

(function () {
  'use strict'
  feather.replace({ 'aria-hidden': 'true' })

  // Graphs
  var ctx = document.getElementById('myChart');
  var week = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
var traffic = [65, 59, 80, 81, 56, 55, 60]
  // eslint-disable-next-line no-unused-vars
  var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: week,
      
      //dữ liệu biểu đồ
      datasets: [{
        data: traffic,
        lineTension: 0,
        backgroundColor: 'transparent',
        borderColor: '#007bff',
        borderWidth: 4,
        pointBackgroundColor: '#007bff'
      }]
    },
    options: {
      scales: {
        yAxes: [{
          ticks: {
            beginAtZero: false
          }
        }]
      },
      legend: {
        display: false
      }
    }
  })
})()
