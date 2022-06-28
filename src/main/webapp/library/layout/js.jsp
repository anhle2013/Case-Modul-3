    <!-- Jquery JS-->
    <script src="/assets/vendor/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="/assets/vendor/bootstrap-4.1/popper.min.js"></script>
    <script src="/assets/vendor/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
<%--    <script src="/assets/vendor/animsition/animsition.min.js"></script>--%>
    <script src="/assets/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <!-- Main JS-->
    <script src="/assets/js/main.js"></script>

    <script>
        function blockInputSearch() {
            let select = document.getElementById('field_name');
            let option = select.options[select.selectedIndex].value;
            if (option === "disable") {
                document.getElementById('search').value = "disable";
                document.getElementById('search').disabled = true;
            } else
                document.getElementById('search').disabled = false;
        }

        function unlockIdGroup() {
            let action =  document.getElementById("action").value;
            if (action === "edit")
                document.getElementById("idGroup").style.display = "block";
            else
                document.getElementById("idGroup").style.display = "none";
        }

    </script>