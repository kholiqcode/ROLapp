/*
 Template Name: Stexo - Responsive Bootstrap 4 Admin Dashboard
 Author: Themesdesign
 Website: www.themesdesign.in
 File: C3 Chart init js
 */

!function ($) {
    "use strict";

    var ChartC3 = function () { };

    ChartC3.prototype.init = function () {

        $.get('http://localhost/civoting/admin/home/chart')
            .done(function (data) {
                var obj = JSON.parse(data);
                // var x = new Array(obj.length);
                var array = [];
                for (var i = 0; i < obj.length; i++) {
                    array[i] = [obj[i].nama, parseInt(obj[i].persentase)];

                }
                //Pie Chart
                c3.generate({
                    bindto: '#pie-chart',
                    data: {
                        columns: array,
                        type: 'pie'
                    },
                    color: {
                        pattern: ["#30419b", "#f0f4f7", "#fcbe2d", "#02c58d"]
                    },
                    pie: {
                        label: {
                            format: function (value, ratio, id) {
                                ratio = d3.format("%")(ratio); // format ratio
                                return [value, ratio].join(); // used to pass values to the onrender function
                            }
                        }
                    }
                });
                console.log(array);
            })

    },
        $.ChartC3 = new ChartC3, $.ChartC3.Constructor = ChartC3

}(window.jQuery),

    //initializing 
    function ($) {
        "use strict";
        $.ChartC3.init()
    }(window.jQuery);



