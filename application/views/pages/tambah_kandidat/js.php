    <!-- XEditable Plugin -->
    <script src="<?= base_url('assets/plugins/moment/moment.js') ?>"></script>
    <script src="<?= base_url('assets/plugins/x-editable/js/bootstrap-editable.min.js') ?>"></script>
    <script src="<?= base_url('assets/pages/xeditable.js') ?>"></script>

    <!-- Form Load -->
    <script src="<?= base_url('assets/pages/form.init.js') ?>"></script>

    <!-- Sweet-Alert  -->
    <script src="<?= base_url('assets/plugins/sweet-alert2/sweetalert2.min.js') ?>"></script>
    <script src="<?= base_url('assets/pages/sweet-alert.init.js') ?>"></script>

    <!-- Parsley js -->
    <script src="<?= base_url('assets/plugins/parsleyjs/parsley.min.js') ?>"></script>

    <script>
        $(document).ready(function() {
            $('form').parsley();
        });
    </script>