new Chart(document.getElementById("ingresos"), {
    type: "line",
    data: {
        labels: ["Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"],
        datasets: [{
            label: "Ingresos",
            data: [850, 1100, 950, 1350, 1250, 1450, 1250],
            borderWidth: 2
        }]
    }
});