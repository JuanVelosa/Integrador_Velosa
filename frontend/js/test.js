var signalChart = document.getElementById('signalChart');
var ctx = signalChart.getContext('2d');

var data = [
    { x: 0.1, y: 10 },
    { x: 0.2, y: 20 },
    { x: 0.3, y: 15 },
    { x: 0.4, y: 25 },
    { x: 15, y: 30 }
  ];

// Configurar y crear el gráfico
var chart = new Chart(ctx, {
  type: 'scatter',
  data: {
    datasets: [{
      data: data,
      showLine: true
    }]
  }
});