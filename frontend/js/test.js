const API = "http://localhost:8080";
const patientDropdown = document.getElementById('patientDropdown');
const registerDropdown = document.getElementById('registerDropdown');
const signalChartCtx = document.getElementById('signalChart').getContext('2d');
let chart = null;

async function fetchPatientRegisters(registerDropdown, patientId) {
  let data = null;
  try {
    let response = await fetch(API + `/patients/${patientId}/registers`);
    data = await response.json();
  } catch (error) {
    console.error('Error al realizar la solicitud:', error);
  }

  if (Array.isArray(data)) {
    registerDropdown.innerHTML = '<option value="">Seleccionar Registro</option>';
    for (const register in data) {
      const option = document.createElement('option');
      option.value = register.id;
      option.text = `Registro ${register.id}`;
      registerDropdown.add(option);
    }
  } else {
    console.log('No se encontraron registros para el paciente.');
  }
}

async function fetchRegisterData(registerId) {
  let data = null;
  try {
    let response = await fetch(API + `/registers/${registerId}/samples`);
    data = await response.json();
  } catch (error) {
    console.error('Error fetching register data:', error);
  }

  // Convert fetched data to chart data
  const testData = data.map(point => ({ x: point.x, y: point.y }));

  // Actualizar los datos de la gráfica sin destruirla
  chart.data.datasets[0].data = testData;
  chart.data.datasets[0].label = `Register ${registerId}`;
  chart.update();
}

// Fetch all patient and populate the dropdown
async function fetchPatients() {
  const res = await fetch(API + '/patients');
  const patients = await res.json();
  patients.forEach(patient => {
    const id = patient['id'];
    const option = document.createElement('option');
    option.value = id;
    option.text = `Paciente ${id}`;
    patientDropdown.add(option);
  });
}

window.onload = function () {



  // Event listener for patient dropdown change
  patientDropdown.addEventListener('change', function () {
    const patientId = patientDropdown.value;
    if (patientId) {
      fetchPatientRegisters(patientId);
    }
  });

  // Event listener for register dropdown change
  registerDropdown.addEventListener('change', function () {
    const registerId = registerDropdown.value;
    if (registerId) {
      fetchRegisterData(registerId);
    }
  });

  const data = fetchRegisterData()

  // Initialize the chart with initial data
  chart = new Chart(signalChartCtx, {
    type: 'scatter',
    data: {
      datasets: [{
        label: 'Initial Data',
        data: data,
        showLine: true,
        borderColor: 'rgba(75, 192, 192, 1)',
        backgroundColor: 'rgba(75, 192, 192, 0.2)',
        pointBorderColor: 'rgba(75, 192, 192, 1)',
        pointBackgroundColor: '#fff',
        pointRadius: 5,
      }]
    },
    options: {
      scales: {
        x: {
          type: 'linear',
          position: 'bottom'
        }
      }
    }
  });
};
