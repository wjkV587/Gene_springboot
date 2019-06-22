

function  analyse_gene() {
    //数据准备
    var max_length = 1;
    var min_group = 2;
    var loop_size_s = 3;
    var loop_size_e = 4;
    var gene_sequence = $('#gene_seq').val();

    var data = {"max_length":max_length, "min_group":min_group, "loop_size_s":loop_size_s, "loop_size_e":loop_size_e, "gene_sequence":gene_sequence};
    var postdata = JSON.stringify(data);

    //alert(postdata)

    //判断基因序列是否仅为‘ATGC’组合
    $.ajax({
        type:'post',
        url:'/Gene/analyse',
        data: postdata,
        dataType:'JSON',
        contentType:'application/json',
        success:function(result){
            if(result){

                var obj = eval(result);
                //获取Data View
                var tbody =$('<tbody></tbody>');

                $(obj.data_view).each(function (index) {
                    var tr = $('<tr></tr>')

                    var val = obj.data_view[index];

                    var qgrs = val.qgrs;
                    var length = val.length;
                    var position = val.position;
                    var score = val.score;
                    var str = '<td>'+position+'</td><td>'+length+'</td><td>'+qgrs+'</td><td>'+score+'</td>';


                    tr.append(str);
                    tbody.append(tr);
                    $('#h1').css('display',"none ");;



                })


                var tt = '#table1'+' tbody';
                $(tt).replaceWith(tbody);



            }
            else{
                alert('Analyze Error!')
            }

        },
        error:function (errorMsg) {
            alert('Analyze Error!')
        }


    })
    
}